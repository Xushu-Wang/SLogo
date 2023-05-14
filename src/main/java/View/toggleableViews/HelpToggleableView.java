package View.toggleableViews;

import Model.HelpData;
import Model.XMLReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class HelpToggleableView extends ToggleableView {

  public HelpToggleableView(int width, int height, String language) {
    super.setResources(language);
    super.setView(makeView());
    super.makeButton("HelpButton", getFromResources("Help"));
    super.setDimensions(width, height);
  }

  @Override
  protected ScrollPane makeView() {
    ScrollPane view = new ScrollPane();
    view.setId("HelpView");

    XMLReader reader = new XMLReader("English");
    List<HelpData> dataList;
    try {
      dataList = reader.loadInstructionData();
    } catch (ParserConfigurationException | IOException | SAXException e) {
      throw new RuntimeException(e);
    }

    Map<String, List<HelpData>> mapSortedByCategory = makeDataMap(dataList);
    Accordion accordion = makeDataAccordion(mapSortedByCategory);
    accordion.maxWidth(MAX_PANE_WIDTH);
    view.setContent(accordion);
    view.maxWidth(MAX_PANE_WIDTH);

    return view;
  }

  @Override
  public void updateView(String context, String info) {
    // does nothing (should not be updated)
  }

  private Accordion makeDataAccordion(Map<String, List<HelpData>> dataMap) {
    Accordion mainAccordion = new Accordion();
    for (String type : dataMap.keySet()) {
      TitledPane subAccordion = new TitledPane(type, makeSubAccordion(dataMap.get(type)));
      mainAccordion.getPanes().add(subAccordion);
    }
    return mainAccordion;
  }

  private Accordion makeSubAccordion(List<HelpData> dataList) {
    Accordion accordion = new Accordion();
    for (HelpData data : dataList) {
      TitledPane infoView = new TitledPane(data.name(), makeDetailedView(data));
      accordion.getPanes().add(infoView);
    }
    return accordion;
  }

  private VBox makeDetailedView(HelpData data) {
    VBox box = new VBox();
    box.getChildren().add(new Text("Alternate name: " + data.alternateName()));
    box.getChildren().add(new Text("Description: " + data.description()));
    box.getChildren().add(new Text("Example: " + data.example()));
    box.getChildren().add(new Text("Return type: " + data.returnType()));
    return box;
  }

  private Map<String, List<HelpData>> makeDataMap(List<HelpData> dataList) {
    Map<String, List<HelpData>> dataMap = new HashMap<>();

    for (HelpData data : dataList) {
      String type = data.instructType();
      dataMap.putIfAbsent(type, new ArrayList<>());
      dataMap.get(type).add(data);
    }

    return dataMap;
  }
}
