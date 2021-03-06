package com.cdkj.loan.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.domain.Node;

@Component
public interface INodeAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 分页查询
    public Paginable<Node> queryNodePage(int start, int limit, Node condition);

    // 列表查询
    public List<Node> queryNodeList(Node condition);

    // 详情查询
    public Node getNode(String code);

}
