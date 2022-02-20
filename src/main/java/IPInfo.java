public class IPInfo {

    public String ip;
    public String city;
    public String region;
    public String country;
    public String loc;
    public String org;
    public String timezone;

    @Override
    public String toString() {
        return "ip='" + ip + '\'' + ",\n" +
                "city='" + city + '\'' + ",\n" +
                "region='" + region + '\'' + ",\n" +
                "country='" + country + '\'' + ",\n" +
                "loc='" + loc + '\'' + ",\n" +
                "org='" + org + '\'' + ",\n" +
                "timezone='" + timezone + '\'';
    }
}
