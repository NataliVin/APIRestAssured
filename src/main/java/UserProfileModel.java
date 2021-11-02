import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserProfileModel {
    private Integer id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private String avatar;

    public UserProfileModel(){
    }

    public UserProfileModel(Integer id, String firstName, String lastName, String email, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserProfileModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" +  + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
