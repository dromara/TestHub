package org.dromara.testhub.plugins.http.server.domain.cache;

import org.apache.commons.collections4.list.UnmodifiableList;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;

import java.util.*;
import java.util.stream.IntStream;

public class TreeCache {

    private List<TreeNodeResDto> treeNode = new ArrayList<>();
    private Map<String, TreeNodeResDto> nodeMap = new HashMap<>();

    public List<TreeNodeResDto> getTree() {
        return new UnmodifiableList<>(treeNode);
    }

    public synchronized void addNode(TreeNodeResDto node) {
        // 如果节点没有父节点（根节点），将其添加到 NODE_TREE
        if ("0".equals(node.getParentKey())) {
            treeNode.add(node);
        } else {
            // 如果节点有父节点，找到父节点并添加为其子节点
            TreeNodeResDto parentNode = nodeMap.get(node.getParentKey());
            if (parentNode != null) {
                parentNode.getChildren().add(node);
            }
        }
        // 将节点添加到 NODE_MAP 中
        nodeMap.put(node.getKey(), node);
    }

    public synchronized void deleteNode(String key) {
        if (nodeMap.containsKey(key)) {
            TreeNodeResDto removedNode = nodeMap.remove(key);
            treeNode.remove(removedNode);

            TreeNodeResDto parentNode = nodeMap.get(removedNode.getParentKey());
            if (parentNode != null) {
                parentNode.getChildren().remove(removedNode);
            }
        }
    }

    /**
     * @param node
     */
    public synchronized void updateNode(TreeNodeResDto node) {
        TreeNodeResDto oldNode = nodeMap.get(node.getKey());
        if (oldNode == null) {
            throw new TestHubException("key不存在");
        }
        oldNode.setName(node.getName());
        oldNode.setData(node.getData());
        if (!oldNode.getParentKey().equals(node.getParentKey())) {
            //父节点不同 老的父节点删除
            if ("0".equals(oldNode.getParentKey())) {
                //老的是根
                treeNode.remove(oldNode);
            } else {
                TreeNodeResDto oldParentNode = nodeMap.get(oldNode.getParentKey());
                if (oldParentNode != null) {
                    oldParentNode.getChildren().remove(oldNode);
                }
            }
            oldNode.setParentKey(node.getParentKey());
            // 然后走新增逻辑
            addNode(oldNode);
        }
    }

    public TreeNodeResDto getNode(String key) {
        return nodeMap.get(key);
    }


    private TreeNodeResDto delRoot(String key) {
        OptionalInt index = IntStream.range(0, treeNode.size())
                .filter(i -> key.equals(treeNode.get(i).getKey()))
                .findFirst();

        if (index.isEmpty()) {
            return null;
        } else {
            return treeNode.remove(index.getAsInt());
        }
    }

}
