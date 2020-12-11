package com.tinellij;

import java.util.ArrayList;
import java.util.List;

/**
 * 다중 포커스
 * ctrl +  ctrl(누른상태) + 방향키
 * 에러 이동
 * F2
 * 
 */
public class FocusCopy {

    public void copyFocus(){
        List<String> members = new ArrayList<>();

        members.add(new Member().getName());
        members.add(new Member().getName());
        members.add(new Member().getName());
        members.add(new Member().getName());
        members.add(new Member().getName());

    }
}
