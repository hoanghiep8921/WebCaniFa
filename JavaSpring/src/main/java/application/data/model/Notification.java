package application.data.model;

import javax.persistence.*;

@Entity(name ="notification" )
public class Notification {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "code")
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
