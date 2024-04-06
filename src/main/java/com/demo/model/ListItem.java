package com.demo.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Contains the information of a list item
 * 
 * @author Jaime Chang
 * 
 */
public class ListItem implements Serializable, Comparable<ListItem> {
    private static final long serialVersionUID = 1L;

    private String value;

    private String label;

    /**
     * This field indicates whether a particular list item should typically be
     * selectable by a user. This allows lists to contain the definition for
     * type codes which are valid in the system but which should not typically
     * be allowed to be set by the user directly.
     */
    private boolean defaultUserSelectable = true;

    /**
     * This element indicates whether a particular list item should typically be
     * considered an 'End State'. If a user editable field starts out with a
     * value which corresponds with a list item with DefaultUserEndState ==
     * true, the user should typically NOT be allowed to change the value. This
     * can be used to aid GUI code in preventing the user from undoing states
     * which typically mark the completion of a process (i.e. values like 'Done'
     * or 'Complete').
     */
    private boolean defaultUserEndState = false;

    public ListItem() {
    }

    public ListItem(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * Convenience method to return label as (Value) Label
     * @return
     */
    public String getCombinedLabel() {
        return "(" + this.value + ") " + this.label;
    }

    /**
     * Return true if this list item represents a value which should typically
     * be allowed to be selected by the user. See javadoc for
     * defaultUserSelectable attribute for more info.
     * 
     * @return the defaultUserSelectable
     */
    public boolean isDefaultUserSelectable() {
        return defaultUserSelectable;
    }

    /**
     * Set to indicate if this list item represents a value which should
     * typically be allowed to be selected by the user. See javadoc for
     * defaultUserSelectable attribute for more info.
     * 
     * @param defaultUserSelectable
     *            the defaultUserSelectable to set
     */
    public void setDefaultUserSelectable(boolean defaultUserSelectable) {
        this.defaultUserSelectable = defaultUserSelectable;
    }

    /**
     * Return true if this list item represents a list item value that
     * represents an 'End State'. See javadoc for defaultUserEndState attribute
     * for more info.
     * 
     * @return the defaultUserEndState
     */
    public boolean isDefaultUserEndState() {
        return defaultUserEndState;
    }

    /**
     * Set to indicate if this list item represents a list item value that
     * represents an 'End State'. See javadoc for defaultUserEndState attribute
     * for more info.
     * 
     * @param defaultUserEndState
     *            the defaultUserEndState to set
     */
    public void setDefaultUserEndState(boolean defaultUserEndState) {
        this.defaultUserEndState = defaultUserEndState;
    }

    /**
     * Compares its two arguments for order.
     * @param o
     * @return
     */
    @Override
    public int compareTo(ListItem o) {
        return OrderListItem.LABEL.compare(this, o);
    }

    /**
     * A comparison static class, which helps on ordering a collection of ListItem objects.
     * An OrderListItem object can be passed to a sort method (such as Collections.sort or Arrays.sort) to allow
     * control over the sort order by ListItem Label or Value.
     */
    public static class OrderListItem {
        public static Comparator<ListItem> LABEL = new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                String listItem1Label = o1.label.toUpperCase();
                String listItem2Label = o2.label.toUpperCase();
                // Ascending Order
                return listItem1Label.compareTo(listItem2Label);
            }
        };

        public static Comparator<ListItem> VALUE = new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                String listItem1Value = o1.value.toUpperCase();
                String listItem2Value = o2.value.toUpperCase();
                // Ascending Order
                return listItem1Value.compareTo(listItem2Value);
            }
        };
    }
}