package edu.jsf.ui;

import com.icesoft.faces.component.tree.IceUserObject;
import edu.jsf.model.Road;
import edu.jsf.model.Sign;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
public class RoadToSignUserObject extends IceUserObject {
    private Road road;
    private Sign sign;

    public RoadToSignUserObject(Road road, DefaultMutableTreeNode node) {
        super(node);
        this.road = road;
    }

    public RoadToSignUserObject(Sign sign, DefaultMutableTreeNode node) {
        super(node);
        this.sign = sign;
    }

    public RoadToSignUserObject(DefaultMutableTreeNode node) {
        super(node);
    }

    public String getImageSource() {
        if (sign != null) {
            return sign.getImageSource();
        }
        return "image/folder.png";
    }

    public String getLabel() {
        if (sign != null) {
            return sign.toString();
        }
        if (road != null) {
            return road.toString();
        }
        return "Roads";
    }
}
