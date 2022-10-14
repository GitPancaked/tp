package computercomponentchooser.components;

public class Memory extends Component {
    protected String speed;
    protected String size;

    public Memory(String name, String price, String power, String size, String speed) {
        this.name = name;
        this.price = price;
        this.power = power;
        this.size = size;
        this.speed = speed;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
