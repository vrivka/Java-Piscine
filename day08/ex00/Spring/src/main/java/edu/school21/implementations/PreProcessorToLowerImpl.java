package edu.school21.implementations;

import edu.school21.interfaces.PreProcessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String invoke(String message) {
        return message.toLowerCase();
    }
}
