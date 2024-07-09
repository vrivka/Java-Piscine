package edu.school21.implementations;

import edu.school21.interfaces.PreProcessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String invoke(String message) {
        return message.toUpperCase();
    }
}
