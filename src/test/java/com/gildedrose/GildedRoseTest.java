package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("fixme", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    @Test
    public void after_sell_by_passed_quality_degrades_twice_as_fast() {
        Item[] items = new Item[] { new Item("item", -1, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 6, app.items[0].quality );
    }

    @Test
    public void quality_can_never_be_negative() {
        Item[] items = new Item[] { new Item("item", -1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 0, app.items[0].quality );
    }

    @Test
    public void aged_brie_increase_quality_when_getting_older() {
        Item[] items = new Item[] { new Item("Aged Brie", 6, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 7, app.items[0].quality );
    }

    @Test
    public void items_quality_is_never_greater_than_50() {
        Item[] items = new Item[] { new Item("Aged Brie", 6, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 50, app.items[0].quality );
    }

    @Test
    public void solfuras_never_decrease_in_quality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 6, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 50, app.items[0].quality );
    }

    @Test
    public void backstage_passes_increase_quality_getting_older() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 20, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 21, app.items[0].quality );
    }

    @Test
    public void backstage_passes_increase_quality_of_2_when_sellin_is_10_days_or_less() {
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 8, 20)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
        assertEquals( 22, app.items[1].quality );
    }

    @Test
    public void backstage_passes_increase_quality_of_3_when_sellin_is_5_days_or_less() {
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 4, 20)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 23, app.items[0].quality );
        assertEquals( 23, app.items[1].quality );
    }

    @Test
    public void backstage_passes_quality_fall_to_0_when_product_has_expired() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 0, app.items[0].quality );
    }

    

}
