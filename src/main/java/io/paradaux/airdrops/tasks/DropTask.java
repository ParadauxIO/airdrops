package io.paradaux.airdrops.tasks;

import io.paradaux.airdrops.core.AirdropManager;

public class DropTask implements Runnable {

    private final AirdropManager manager;

    public DropTask(AirdropManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        manager.drop();
    }



}
