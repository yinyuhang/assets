package com.hfnu.assets;

import com.hfnu.assets.pojo.Asset;
import com.shark.generator.Generator;
import org.junit.Test;

public class TestTest {
    @Test
    public void test1 () {
        Generator.init(Asset.class);
    }
}
