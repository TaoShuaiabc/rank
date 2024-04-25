package com.leaf.springbootleaf;


import com.leaf.springbootleaf.common.Result;

public interface IDGen {
    Result get(String key);

    boolean init();
}
