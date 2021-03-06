package org.laughingpanda.wicket;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

class RowDataView<T extends Serializable> extends DataView<T> {
    private final LazyLoadScrollableList<T> list;
    private final int offset;

    public RowDataView(final String id, LazyLoadScrollableList<T> list, int offset) {
        super(id, new ProxyDataProvider<T>());
        this.list = list;
        this.offset = offset;
    }

    public void setDataProvider(final SublistDataProvider<T> dataProvider) {
        ((ProxyDataProvider<T>) getDataProvider()).setDataProvider(dataProvider);
    }

    @Override
    protected void populateItem(final Item<T> item) {
        T modelObject = item.getModelObject();
        item.add(new AttributeModifier("class", true, new Model<String>("loaded-row")));
        list.populateRow(item, offset + item.getIndex(), modelObject);
    }
}
