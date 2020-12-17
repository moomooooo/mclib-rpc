package mchorse.mclib.client.gui.framework.elements.list;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import mchorse.mclib.McLib;
import mchorse.mclib.client.gui.framework.elements.list.GuiListElement;
import mchorse.mclib.client.gui.framework.elements.list.GuiStringListElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * Similar to {@link GuiStringListElement}, but uses {@link ResourceLocation}s 
 */
public class GuiResourceLocationListElement extends GuiListElement<ResourceLocation>
{
    public GuiResourceLocationListElement(Minecraft mc, Consumer<List<ResourceLocation>> callback)
    {
        super(mc, callback);

        this.scroll.scrollItemSize = 16;
    }

    @Override
    protected boolean sortElements()
    {
        Collections.sort(this.list, (a, b) -> a.toString().compareToIgnoreCase(b.toString()));

        return true;
    }
}