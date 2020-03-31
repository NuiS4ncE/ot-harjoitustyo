package teoskanta.domain;


public class User {
    private String username;
    private String password;
    
    public User(String username, String password){
        this.password = password;
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getUsername(){
        return username;
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
