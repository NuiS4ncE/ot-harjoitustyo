package teoskanta.domain;

import teoskanta.user.dao.DBUserDao;

public class Title implements Comparable<Title> {

    private int id;
    private int userid;
    private String year;
    private String name;
    private String genre;
    private String author;
    private DBUserDao userdao;
    
    /**
    * Constructor for Title class 
    * @param name String-type variable for name input
    * @param author String-type variable for author input
    * @param year String-type variable for year input
    * @param userid int-type variable for userid input
    */
    public Title(String name, String author, String year, int userid) {
        this.name = name;
        this.userid = userid;
        this.author = author;
        this.year = year;
    }
    
    /**
    * Constructor for Title class 
    * @param id int-type variable for title id number input
    * @param name String-type variable for name input
    * @param author String-type variable for author input
    * @param year String-type variable for year input
    * @param userid int-type variable for userid input
    */
    public Title(int id, String name, String author, String year, int userid) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.author = author;
        this.year = year;
    }
    
    /**
    * Constructor for Title class 
    * @param name String-type variable for name input
    * @param author String-type variable for author input
    * @param user User-type variable for user object input
    */
    public Title(String name, String author, User user) {
        this.name = name;
        this.author = author;
        this.userid = user.getId();
    }
    /**
    * Empty constructor for Title class
    */
    public Title() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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

    @Override
    public int compareTo(Title title) {
        return this.name.compareTo(title.getAuthor());
    }
}
