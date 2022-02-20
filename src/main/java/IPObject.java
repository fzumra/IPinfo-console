import com.google.gson.annotations.SerializedName;

public class IPObject {
    @SerializedName("ip")
    public String ip;

    @Override
    public String toString() {
        return "IPObject{" +
                "ip='" + ip + '\'' +
                '}';
    }
}
