package Model;

/**
 * @Purpose: Immutable Record class to pass instruction help information to controller.
 * Contains instruction name, alternate name, description, example usage, return type, and instruction type.
 * @author Jay Yoon
 *
 */
public record HelpData(String name,
                       String alternateName,
                       String description,
                       String example,
                       String returnType,
                       String instructType) {

}
