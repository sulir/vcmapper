package com.github.sulir.vcdemo.mapper;

public class CommandExecutor {
    private CommandIndex index = new CommandIndex();

    public CommandExecutor(Object[] objects) {
        for (Object object : objects)
            index.addObject(object);
    }

    public void execute(String sentence) {
        for (Command command : index.getCommands()) {
            if (command.matches(sentence)) {
                command.execute();
                break;
            }
        }
    }
}
