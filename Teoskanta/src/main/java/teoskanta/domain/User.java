package teoskanta.domain;


public class User {
    private String username;
    private String password;
    private int id;
    
    public User(String username, String password){
        //this.id = id;
        this.password = password;
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof User)){
            return false;
        }
        
        User other = (User) obj;
        return username.equals(other.username);
    }
    
}
