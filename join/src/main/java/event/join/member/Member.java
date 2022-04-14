package event.join.member;

public class Member {
    private String id;
    private String pwd;
    private String nick;
    private String phone;
    private String area;

    public Member(String id, String pwd, String nick, String phone, String area) {
        this.id = id;
        this.pwd = pwd;
        this.nick = nick;
        this.phone = phone;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
