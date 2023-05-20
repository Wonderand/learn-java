package com.huzhirong.voice_demo.JPythonDemo;

import org.python.util.PythonInterpreter;

public class test {

    public static void main(String[] args) {

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("D:\\javaPythonFile.py");
//        interpreter.exec("a='hello world'; ");
//        interpreter.exec("print a;");
    }
}
