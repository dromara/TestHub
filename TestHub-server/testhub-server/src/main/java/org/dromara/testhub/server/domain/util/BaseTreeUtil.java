package org.dromara.testhub.server.domain.util;

import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeDataResDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto2;
import org.dromara.testhub.server.infrastructure.repository.po.TreeInfoPo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTreeUtil {
    private static String ROOT = "0";
    public static String TYPE_DIR = "DIR";
    public static String TYPE_CASE = "CASE";

    public static Map<String, TreeNodeResDto2> convert2(List<TreeInfoPo> treeInfoPos) {
        Map<String, TreeNodeResDto2> map = new HashMap<>();
        TreeNodeResDto2 root = new TreeNodeResDto2();
        root.setIndex(ROOT);
        root.setIsFolder(true);
        TreeNodeDataResDto rootNodeData = new TreeNodeDataResDto();
        rootNodeData.setName("");
        rootNodeData.setKey(ROOT);
        root.setData(rootNodeData);
        map.put(ROOT, root);

        for (TreeInfoPo node : treeInfoPos) {
            map.put(getKey(node), getTreeNodeResDto(node));
        }

        for (TreeInfoPo node : treeInfoPos) {
            map.get(getParentKey(node)).getChildren().add(getKey(node));
        }

        return map;
    }
    private static String getKey(TreeInfoPo po) {
        return String.valueOf(po.getId());
    }

    private static String getParentKey(TreeInfoPo po) {
        return po.getParentId() == 0 ? ROOT : String.valueOf(po.getParentId());
    }

    public static TreeNodeResDto2 getTreeNodeResDto(TreeInfoPo po) {
        TreeNodeResDto2 treeNodeResDto2 = new TreeNodeResDto2();
        treeNodeResDto2.setIsFolder(TYPE_DIR.equalsIgnoreCase(po.getNodeType()));
        TreeNodeDataResDto nodeDataResDto = new TreeNodeDataResDto();
        nodeDataResDto.setKey(getKey(po));
        nodeDataResDto.setName(po.getName());
        nodeDataResDto.setNodeType(po.getNodeType());
        nodeDataResDto.setParentKey(getParentKey(po));
//        nodeDataResDto.setInfo(po);
        treeNodeResDto2.setData(nodeDataResDto);
        treeNodeResDto2.setIndex(getKey(po));
        return treeNodeResDto2;
    }

    public static List<TreeNodeResDto> convert(List<HttpTreeNodePo> httpTreeNodes) {
        List<TreeNodeResDto> treeNodeResDtos = new ArrayList<>();
        // 创建一个映射，将 id 映射到相应的节点
        Map<Long, TreeNodeResDto> nodeMap = new HashMap<>();

        for (HttpTreeNodePo httpTreeNode : httpTreeNodes) {
            TreeNodeResDto treeNodeResDto = new TreeNodeResDto();
            treeNodeResDto.setKey(String.valueOf(httpTreeNode.getId()));
            treeNodeResDto.setParentKey(String.valueOf(httpTreeNode.getParentId()));
            treeNodeResDto.setName(httpTreeNode.getName());
            treeNodeResDto.setNodeType(httpTreeNode.getNodeType());

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
