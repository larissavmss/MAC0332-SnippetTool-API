package br.com.usp.mac0332.snippettool;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.User;

import java.sql.Date;
import java.time.LocalDate;

public class TestUtils {

    public static User createUser(){
        return new User(
                1,
                "asnjan",
                "snbjnas",
                "sjsa",
                Date.valueOf(LocalDate.now())
        );
    }

    public static Folder createFolder(){
        return new Folder(
                "test",
                createUser()
        );
    }

}
