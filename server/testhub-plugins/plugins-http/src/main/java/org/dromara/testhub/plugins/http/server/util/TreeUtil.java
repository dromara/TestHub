package org.dromara.testhub.plugins.http.server.util;

import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {

    public static List<TreeNodeResDto> convert(List<HttpTreeNodePo> httpTreeNodes,String type) {
        List<TreeNodeResDto> treeNodeResDtos = new ArrayList<>();
        // 创建一个映射，将 id 映射到相应的节点
        Map<Long, TreeNodeResDto> nodeMap = new HashMap<>();

        for (HttpTreeNodePo httpTreeNode : httpTreeNodes) {
            TreeNodeResDto treeNodeResDto = new TreeNodeResDto();
            treeNodeResDto.setKey(String.valueOf(httpTreeNode.getId()));
            treeNodeResDto.setParentKey(String.valueOf(httpTreeNode.getParentId()));
            treeNodeResDto.setName(httpTreeNode.getName());
            treeNodeResDto.setNodeType(type);

            // 将节点放入映射
            nodeMap.put(httpTreeNode.getId(), treeNodeResDto);

            if (httpTreeNode.getParentId() == 0) {
                // 根节点，直接添加到结果列表
                treeNodeResDtos.add(treeNodeResDto);
            }
        }

        // 遍历构建树
        for (HttpTreeNodePo httpTreeNode : httpTreeNodes) {
            if (httpTreeNode.getParentId() != 0) {
                TreeNodeResDto parent = nodeMap.get(httpTreeNode.getParentId());
                if (parent != null) {
                    // 添加当前节点到父节点的子节点列表
                    parent.getChildren().add(nodeMap.get(httpTreeNode.getId()));
                }
            }
        }

        return treeNodeResDtos;
    }
}
