package org.dromara.testhub.server.core.util;

import cn.hutool.core.collection.CollectionUtil;
import org.dromara.testhub.server.domain.dto.res.other.TreeNodeResDto;
import org.dromara.testhub.server.infrastructure.repository.po.TreeInfoPo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {
    public static List<TreeNodeResDto> convert(List<TreeInfoPo> treeInfoList) {
        if(CollectionUtil.isEmpty(treeInfoList)){
            return new ArrayList<>();
        }
        List<TreeNodeResDto> treeNodeList = new ArrayList<>();
        for (TreeInfoPo treeInfo : treeInfoList) {
            if (treeInfo.getParentId() == 0) {
                TreeNodeResDto treeNode = new TreeNodeResDto();
                treeNode.setKey(treeInfo.getId().toString());
                treeNode.setName(treeInfo.getName());
                treeNode.setNodeType(treeInfo.getNodeType());
                treeNode.setChildren(getChildren(treeInfo.getId(), treeInfoList));
                treeNodeList.add(treeNode);
            }
        }
        return treeNodeList;
    }

    private static List<TreeNodeResDto> getChildren(Long parentId, List<TreeInfoPo> treeInfoList) {
        if(CollectionUtil.isEmpty(treeInfoList)){
            return new ArrayList<>();
        }
        List<TreeNodeResDto> children = new ArrayList<>();
        for (TreeInfoPo treeInfo : treeInfoList) {
            if (parentId.equals(treeInfo.getParentId())) {
                TreeNodeResDto treeNode = new TreeNodeResDto();
                treeNode.setKey(treeInfo.getId().toString());
                treeNode.setName(treeInfo.getName());
                treeNode.setNodeType(treeInfo.getNodeType());
                treeNode.setParentKey(parentId+"");
                treeNode.setChildren(getChildren(treeInfo.getId(), treeInfoList));
                children.add(treeNode);
            }
        }
        return children;
    }

    public static Map<String, TreeNodeResDto> buildTreeNodeMap(List<TreeNodeResDto> treeNodeList) {
        Map<String, TreeNodeResDto> treeNodeMap = new HashMap<>();
        buildTreeNodeMapRecursive(treeNodeList, treeNodeMap);
        return treeNodeMap;
    }

    private static void buildTreeNodeMapRecursive(List<TreeNodeResDto> treeNodeList, Map<String, TreeNodeResDto> treeNodeMap) {
        for (TreeNodeResDto treeNode : treeNodeList) {
            treeNodeMap.put(treeNode.getKey(), treeNode);
            if (treeNode.getChildren() != null && !treeNode.getChildren().isEmpty()) {
                buildTreeNodeMapRecursive(treeNode.getChildren(), treeNodeMap);
            }
        }
    }

}
