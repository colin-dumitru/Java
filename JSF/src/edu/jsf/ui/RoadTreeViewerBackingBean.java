package edu.jsf.ui;

import edu.jsf.dao.JpaService;
import edu.jsf.model.Road;
import edu.jsf.model.Sign;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Collection;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@ManagedBean
@ViewScoped
public class RoadTreeViewerBackingBean {
    @ManagedProperty("#{jpaService}")
    private JpaService jpaService;

    private DefaultTreeModel defaultTreeModel;

    @PostConstruct
    public void init() {
        defaultTreeModel = generateTreeModel();
    }

    public JpaService getJpaService() {
        return jpaService;
    }

    public void setJpaService(JpaService jpaService) {
        this.jpaService = jpaService;
    }

    public DefaultTreeModel generateTreeModel() {
        DefaultMutableTreeNode rootNode = createRootNode();
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

        Collection<Road> roads = getRoads();
        for (Road road : roads) {
            DefaultMutableTreeNode roadNode = addNode(road, rootNode);

            Collection<Sign> signs = getSigns(road);
            for (Sign sign : signs) {
                addNode(sign, roadNode);
            }
        }

        return treeModel;
    }

    public DefaultTreeModel getTreeModel() {
        return defaultTreeModel;
    }

    public void setTreeModel(DefaultTreeModel defaultTreeModel) {
        this.defaultTreeModel = defaultTreeModel;
    }

    private Collection<Sign> getSigns(Road road) {
        return jpaService.getEntityManager().createQuery("select sp.sign from SignPlacement sp " +
                "where sp.road.id=:roadId", Sign.class)
                .setParameter("roadId", road.getId())
                .getResultList();
    }

    private DefaultMutableTreeNode addNode(Sign sign, DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode();
        RoadToSignUserObject userObject = new RoadToSignUserObject(sign, node);
        userObject.setLeaf(true);
        node.setUserObject(userObject);
        parent.add(node);
        return node;
    }

    private DefaultMutableTreeNode addNode(Road road, DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode();
        RoadToSignUserObject userObject = new RoadToSignUserObject(road, node);
        userObject.setLeaf(false);
        node.setUserObject(userObject);
        parent.add(node);
        return node;
    }

    private DefaultMutableTreeNode createRootNode() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode();
        node.setUserObject(new RoadToSignUserObject(node));
        return node;
    }

    public String refresh() {
        return "success";
    }

    public Collection<Road> getRoads() {
        return jpaService.getEntityManager().createQuery("select r from Road r", Road.class)
                .getResultList();
    }
}
