package teoskanta.domain;


public class Title {

private int id;
private String name;
private String genre;
//private String subgenre; tarvitaanko?
private User user;
    
    public Title(int id, String name, String genre, User user) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.user = user;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getGenre(){
        return this.genre;
    }
}
