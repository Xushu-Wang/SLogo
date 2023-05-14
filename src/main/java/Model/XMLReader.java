package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @Purpose: XML Reader that reads XML file containing Instruction Data
 * Used in displaying Help Information on various instructions
 *
 * @author Jay Yoon
 *
 */
public class XMLReader {
  public static final String FILE_PATH = "data/";
  public static final String FILE_NAME = "InstructionData";
  public static final String FILE_EXTENSION = ".xml";
  public static final String INSTRUCTION_TAG = "Instruction";
  public static final String NAME_TAG = "Name";
  public static final String ALTER_TAG = "Alternate";
  public static final String DESC_TAG = "Description";
  public static final String EXAMPLE_TAG = "Example";
  public static final String RETURN_TAG = "ReturnType";
  public static final String TYPE_TAG = "CommandType";
  private final ArrayList<String> name = new ArrayList<>();;
  private final ArrayList<String> alternate = new ArrayList<>();;
  private final ArrayList<String> description = new ArrayList<>();;
  private final ArrayList<String> example = new ArrayList<>();
  private final ArrayList<String> returnType = new ArrayList<>();
  private final ArrayList<String> instructType = new ArrayList<>();
  private final File file;

  public XMLReader(String language) {
    file = new File(FILE_PATH+FILE_NAME+language+FILE_EXTENSION);
  }

  /**
   * returns List of HelpData records that contains name, alternate name, description, example usage, return type and instruction type
   */
  public List<HelpData> loadInstructionData() throws ParserConfigurationException, IOException, SAXException {
    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

    Map<String, List<String>> tagToListMap = new HashMap<>() {{
      put(NAME_TAG, name);
      put(ALTER_TAG, alternate);
      put(DESC_TAG, description);
      put(EXAMPLE_TAG, example);
      put(RETURN_TAG, returnType);
      put(TYPE_TAG, instructType);
    }};

    for (Map.Entry<String, List<String>> entry : tagToListMap.entrySet()) {
      String tag = entry.getKey();
      List<String> attributeList = entry.getValue();
      this.loadAttributes(doc, attributeList, tag);
    }

    return this.generateHelpData();
  }

  private void loadAttributes(Document doc, List<String> arr, String tagName) {
    NodeList nodeList = doc.getElementsByTagName(INSTRUCTION_TAG);
    for (int i = 0; i < nodeList.getLength(); i++) {
      Element e = (Element) nodeList.item(i);
      arr.add(e.getElementsByTagName(tagName).item(0).getTextContent());
    }
  }

  private List<HelpData> generateHelpData() {
    ArrayList<HelpData> helpCollection = new ArrayList<>();
    for (int i = 0; i < name.size(); i++) {
      HelpData help = new HelpData(name.get(i), alternate.get(i), description.get(i),
          example.get(i), returnType.get(i), instructType.get(i));
      helpCollection.add(help);
    }
    return helpCollection;
  }

}
