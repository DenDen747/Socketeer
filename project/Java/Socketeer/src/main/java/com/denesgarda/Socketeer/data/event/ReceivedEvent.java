package com.denesgarda.Socketeer.data.event;

import com.denesgarda.Socketeer.data.Connection;

public abstract class ReceivedEvent extends Event {
    private final Connection connection;

    public ReceivedEvent(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public abstract Object read() throws Exception;

    public abstract void reply(Object object) throws Exception;
}