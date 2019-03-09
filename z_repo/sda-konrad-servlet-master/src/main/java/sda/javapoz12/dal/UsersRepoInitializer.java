package sda.javapoz12.dal;

public class UsersRepoInitializer {
     public static UsersRepo getInstnace(){
        //return UsersRepoInMemory.USERS; //uncoment when using in memory repository
        return  UsersRepoInDB.USERS; //uncoment when using in DB repository
    }
}
