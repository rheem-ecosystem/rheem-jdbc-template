package io.rheem.jdbc.test;

import io.rheem.core.platform.ChannelDescriptor;
import io.rheem.jdbc.operators.JdbcFilterOperator;
import io.rheem.jdbc.operators.JdbcProjectionOperator;

import java.util.List;

/**
 * Test implementation of {@link JdbcFilterOperator}.
 */
public class HsqldbProjectionOperator extends JdbcProjectionOperator {

    public HsqldbProjectionOperator(String... names) {
        super(names);
    }

    @Override
    public HsqldbPlatform getPlatform() {
        return HsqldbPlatform.getInstance();
    }

    @Override
    public List<ChannelDescriptor> getSupportedInputChannels(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ChannelDescriptor> getSupportedOutputChannels(int index) {
        throw new UnsupportedOperationException();
    }
}
