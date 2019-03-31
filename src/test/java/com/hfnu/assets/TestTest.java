package com.hfnu.assets;

import com.hfnu.assets.pojo.*;
import com.shark.generator.FileType;
import com.shark.generator.Generator;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestTest {
    @Test
    public void test1() {
        Generator.init(Asset.class, User.class, Borrow.class, Log.class);
    }

    @Test
    public void test2() {
        try {
            Class<?> [] clses = {User.class, Borrow.class, Log.class};
            FileType [] types = {FileType.HTML, FileType.JS};
            for (Class<?> cls : clses) {
                for (FileType type: types) {
                    Generator.init(cls, type);
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}