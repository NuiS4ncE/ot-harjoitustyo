package teoskanta.domain;

import teoskanta.user.dao.DBUserDao;

public class Title {
    
    private int id;
    private int userid;
    private String year;
    private String name;
    private String genre;
    private String author;
//private String subgenre; tarvitaanko?
    private DBUserDao userdao;

    public Title(String name, String author, String year, int userid) {
        this.name = name;
        this.userid = userid;
        this.author = author;
        this.year = year;
    }
    
    public Title(int id, String name, String author, String year, int userid) {
        this.id = id;
        this.name = name;
        //this.genre = genre;
        this.userid = userid;
        this.author = author;
        this.year = year;
    }

    public Title(String name, String author, User user) {
        this.name = name;
        this.author = author;

    }

    public Title() {

    }

    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    } 

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getYear() {
        return this.year;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getUserId() {
        return this.userid;
    }
}
