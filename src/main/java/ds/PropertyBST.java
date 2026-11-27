/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds;

import model.Property;
import java.util.ArrayList;
import java.util.List;

public class PropertyBST {
    public static class Node {
        public Property property;
        public Node left, right;

        public Node(Property property) {
            this.property = property;
            left = right = null;
        }
    }

    private Node root;

    public void insert(Property property) {
        root = insertRec(root, property);
    }

    private Node insertRec(Node root, Property property) {
        if (root == null) {
            return new Node(property);
        }
        if (property.getPrice() < root.property.getPrice()) {
            root.left = insertRec(root.left, property);
        } else {
            root.right = insertRec(root.right, property);
        }
        return root;
    }

    public List<Property> inOrderTraversal() {
        List<Property> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }

    private void inOrderRec(Node root, List<Property> list) {
        if (root != null) {
            inOrderRec(root.left, list);
            list.add(root.property);
            inOrderRec(root.right, list);
        }
    }
}