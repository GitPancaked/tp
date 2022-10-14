package computercomponentchooser;

import computercomponentchooser.components.Component;

import java.util.HashMap;
import java.util.Map;


public class Build {
    private String name;
    private LinkedHashMap2D<Component> components = new LinkedHashMap2D<Component>();

    public Build(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addComponent(String type, Component component) {
        switch (type) {
        case "cpu":
            components.addElement("cpu", component.getName(), component);
            break;
        case "memory":
            components.addElement("memory", component.getName(), component);
            break;
        case "motherboard":
            components.addElement("motherboard", component.getName(), component);
            break;
        case "powersupply":
            components.addElement("powersupply", component.getName(), component);
            break;
        case "gpu":
            components.addElement("gpu", component.getName(), component);
            break;
        case "drive":
            components.addElement("drive", component.getName(), component);
            break;
        default:
            // throw exception
            break;
        }
    }

    public void deleteComponent(String type, String name) {
        components.removeElement(type, name);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String name : components.getNameList()) {
            sb.append(i).append(". ").append(name).append(System.lineSeparator());
            i++;
        }
        return sb.toString();
    }


}
