package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private enum itemType {
        AGED_BRIE,
        SULFURAS,
        CONJURED,
        BACKSTAGE_PASSES,
        OTHERS
    }

    private itemType getItemType(String itemName) {
        if (itemName.equals("Aged Brie")) {
            return itemType.AGED_BRIE;
        } else if (itemName.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return itemType.BACKSTAGE_PASSES;
        } else if (itemName.equals("Sulfuras, Hand of Ragnaros")) {
            return itemType.SULFURAS;
        } else if (itemName.equals("Conjured")) {
            return itemType.CONJURED;
        } else {
            return itemType.OTHERS;
        }
    }


    public void updateQuality() {
        for (Item item : items) {

            switch( getItemType(item.name) ) {
                case AGED_BRIE:
                    incrementQuality(item, 1);
                    item.sellIn = item.sellIn - 1;
                    break;
                case BACKSTAGE_PASSES:
                    item.sellIn = item.sellIn - 1;
                    incrementQuality(item, 1);
                    if (item.sellIn < 11) {
                        incrementQuality(item, 1);
                    }
                    if (item.sellIn < 6) {
                        incrementQuality(item, 1);
                    }
                    if (item.sellIn < 0) {
                        item.quality = 0;
                    }
                    break;
                case SULFURAS:

                    break;
                case CONJURED:
                    decrementQuality(item, 2);
                    item.sellIn = item.sellIn - 1;
                    break;
                case OTHERS:
                    decrementQuality(item, 1);
                    item.sellIn = item.sellIn - 1;
                    break;
            }
        }
    }

    private void decrementQuality(Item item, int decrementOf) {
        if (item.quality > 0)
            item.quality = item.quality - decrementOf;
        if (item.sellIn < 0 && item.quality > 0)
            item.quality = item.quality - 1;
    }

    private void incrementQuality(Item item, int incrementOf) {
        if (item.quality < 50)
            item.quality = item.quality + incrementOf;
    }
}