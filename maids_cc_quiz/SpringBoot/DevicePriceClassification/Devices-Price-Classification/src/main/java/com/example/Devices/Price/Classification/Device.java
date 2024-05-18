package com.example.Devices.Price.Classification;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Represents a mobile device.
 */
@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Device specifications
    private Integer battery_power; // Battery power in mAh
    private Boolean blue; // Indicates if the device has Bluetooth capability
    private Double clock_speed; // Clock speed in GHz
    private Boolean dual_sim; // Indicates if the device supports dual SIM
    private Integer fc; // Front camera resolution in megapixels
    private Boolean four_g; // Indicates if the device supports 4G connectivity
    private Integer int_memory; // Internal memory in GB
    private Double m_dep; // Thickness of the device in cm
    private Integer mobile_wt; // Weight of the device in grams
    private Integer n_cores; // Number of processor cores
    private Integer pc; // Primary camera resolution in megapixels
    private Integer px_height; // Pixel height of the device screen
    private Integer px_width; // Pixel width of the device screen
    private Integer ram; // RAM capacity in MB
    private Integer sc_h; // Screen height in cm
    private Integer sc_w; // Screen width in cm
    private Integer talk_time; // Talk time in hours
    private Boolean three_g; // Indicates if the device supports 3G connectivity
    private Boolean touch_screen; // Indicates if the device has a touch screen
    private Boolean wifi; // Indicates if the device supports Wi-Fi connectivity

    // Constructors
    /**
     * Default constructor for Device class.
     */
    public Device() {
    }

    // Getters and Setters

    /**
     * Retrieves the ID of the device.
     * @return The ID of the device.
     */
    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBattery_power() {
        return battery_power;
    }

    public void setBattery_power(Integer battery_power) {
        this.battery_power = battery_power;
    }

    public Boolean getBlue() {
        return blue;
    }

    public void setBlue(Boolean blue) {
        this.blue = blue;
    }

    public Double getClock_speed() {
        return clock_speed;
    }

    public void setClock_speed(Double clock_speed) {
        this.clock_speed = clock_speed;
    }

    public Boolean getDual_sim() {
        return dual_sim;
    }

    public void setDual_sim(Boolean dual_sim) {
        this.dual_sim = dual_sim;
    }

    public Integer getFc() {
        return fc;
    }

    public void setFc(Integer fc) {
        this.fc = fc;
    }

    public Boolean getFour_g() {
        return four_g;
    }

    public void setFour_g(Boolean four_g) {
        this.four_g = four_g;
    }

    public Integer getInt_memory() {
        return int_memory;
    }

    public void setInt_memory(Integer int_memory) {
        this.int_memory = int_memory;
    }

    public Double getM_dep() {
        return m_dep;
    }

    public void setM_dep(Double m_dep) {
        this.m_dep = m_dep;
    }

    public Integer getMobile_wt() {
        return mobile_wt;
    }

    public void setMobile_wt(Integer mobile_wt) {
        this.mobile_wt = mobile_wt;
    }

    public Integer getN_cores() {
        return n_cores;
    }

    public void setN_cores(Integer n_cores) {
        this.n_cores = n_cores;
    }

    public Integer getPc() {
        return pc;
    }

    public void setPc(Integer pc) {
        this.pc = pc;
    }

    public Integer getPx_height() {
        return px_height;
    }

    public void setPx_height(Integer px_height) {
        this.px_height = px_height;
    }

    public Integer getPx_width() {
        return px_width;
    }

    public void setPx_width(Integer px_width) {
        this.px_width = px_width;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getSc_h() {
        return sc_h;
    }

    public void setSc_h(Integer sc_h) {
        this.sc_h = sc_h;
    }

    public Integer getSc_w() {
        return sc_w;
    }

    public void setSc_w(Integer sc_w) {
        this.sc_w = sc_w;
    }

    public Integer getTalk_time() {
        return talk_time;
    }

    public void setTalk_time(Integer talk_time) {
        this.talk_time = talk_time;
    }

    public Boolean getThree_g() {
        return three_g;
    }

    public void setThree_g(Boolean three_g) {
        this.three_g = three_g;
    }

    public Boolean getTouch_screen() {
        return touch_screen;
    }

    public void setTouch_screen(Boolean touch_screen) {
        this.touch_screen = touch_screen;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }
}
