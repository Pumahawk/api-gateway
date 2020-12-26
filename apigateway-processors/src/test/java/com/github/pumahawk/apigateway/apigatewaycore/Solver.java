package com.github.pumahawk.apigateway.apigatewaycore;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.pumahawk.apigateway.itf.configurations.GatewayConfiguration;
import com.github.pumahawk.apigateway.itf.configurations.SolverConfiguration;

import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.BooleanSpec.BooleanOpSpec;

public class Solver {

    private SolverConfiguration sc;
    private BooleanOpSpec  pr;

    public Solver(SolverConfiguration configuration) {
        this.sc = configuration;
        initBooleanOpSpec();
    }

    public void solve(GatewayConfiguration gc) {
        sc.solve(pr, gc);
    }

    public BooleanOpSpec getBuilder() {
        return pr;
    }

    private void initBooleanOpSpec() {
        pr = mock(BooleanOpSpec.class);
        BooleanSpec bl = mock(BooleanSpec.class);
        when(bl.or()).thenReturn(pr);
        when(pr.alwaysTrue()).thenReturn(bl);
    }
}
