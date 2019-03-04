package com.hfnu.assets;

import com.hfnu.assets.pojo.Asset;
import com.hfnu.assets.pojo.Borrow;
import com.hfnu.assets.pojo.Log;
import com.hfnu.assets.pojo.User;
import com.shark.generator.Generator;
import org.junit.Test;

public class TestTest {
    @Test
    public void test1 () {
        Generator.init(Asset.class, User.class, Borrow.class, Log.class);
    }
}
