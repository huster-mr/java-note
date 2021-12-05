package design.pattern.builder;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ComputerBuilder {
    private String cpu;//必须
    private String ram;//必须
    private int usbCount;//可选
    private String keyboard;//可选
    private String display;//可选

    public Builder custom() {
        return new Builder();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public int getUsbCount() {
        return usbCount;
    }

    public void setUsbCount(int usbCount) {
        this.usbCount = usbCount;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public static class Builder{
        private String cpu;//必须
        private String ram;//必须
        private int usbCount;//可选
        private String keyboard;//可选
        private String display;//可选

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder usbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }

        public Builder keyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder display(String display) {
            this.display = display;
            return this;
        }

        public ComputerBuilder builder() {
            ComputerBuilder instance = new ComputerBuilder();
            instance.setCpu(this.cpu);
            instance.setRam(this.ram);
            instance.setUsbCount(this.usbCount);
            instance.setKeyboard(this.keyboard);
            instance.setDisplay(this.display);
            return instance;
        }
    }
}
