package mchorse.mclib.client.gui.framework.elements;

import mchorse.mclib.McLib;
import mchorse.mclib.client.gui.framework.GuiBase;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.utils.GuiContext;
import mchorse.mclib.client.gui.framework.elements.utils.GuiDrawable;
import mchorse.mclib.client.gui.utils.Area;
import mchorse.mclib.client.gui.utils.Icon;
import mchorse.mclib.client.gui.utils.Keybind;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.client.gui.utils.resizers.Flex.Measure;
import mchorse.mclib.utils.Direction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.util.ArrayList;
import java.util.List;

/**
 * Panel base GUI
 * 
 * With this base class, you can add multi panel elements which could be 
 * switched between using buttons.
 */
public class GuiPanelBase<T extends GuiElement> extends GuiElement
{
    public GuiDelegateElement<T> view;
    public GuiElements<GuiIconElement> buttons;
    public List<T> panels = new ArrayList<T>();
    public Direction direction;

    public GuiPanelBase(Minecraft mc)
    {
        this(mc, Direction.BOTTOM);
    }

    public GuiPanelBase(Minecraft mc, Direction direction)
    {
        super(mc);

        this.direction = direction == null ? Direction.BOTTOM : direction;
        this.view = new GuiDelegateElement<T>(mc, null);
        this.view.flex().relative(this).w(1F).h(1F, -20);

        if (this.direction == Direction.TOP)
        {
            this.view.flex().y(20);
        }
        else if (this.direction == Direction.LEFT)
        {
            this.view.flex().x(20).w(1, -20).h(1, 0);
        }
        else if (this.direction == Direction.RIGHT)
        {
            this.view.flex().w(1, -20).h(1, 0);
        }

        this.buttons = new GuiElements<GuiIconElement>(this);
        this.add(this.view, new GuiDrawable(this::drawOverlay), this.buttons);
    }

    /**
     * Register a panel with given texture and tooltip 
     */
    public GuiIconElement registerPanel(T panel, IKey tooltip, Icon icon)
    {
        GuiIconElement button = new GuiIconElement(this.mc, icon, (b) -> this.setPanel(panel));

        if (tooltip != null && !tooltip.get().isEmpty())
        {
            button.tooltip(tooltip, this.direction.opposite());
        }

        panel.markContainer();
        this.setupButtonResizer(button);
        this.panels.add(panel);
        this.buttons.add(button);

        return button;
    }

    public Keybind registerKeybind(GuiIconElement element, IKey label, int key)
    {
        return this.keys().register(label, key, () -> element.clickItself(GuiBase.getCurrent()));
    }

    /**
     * Here subclasses can override the logic for how the buttons should 
     * be setup 
     */
    protected void setupButtonResizer(GuiIconElement button)
    {
        if (this.buttons.elements.isEmpty())
        {
            if (this.direction.isHorizontal())
            {
                button.flex().relative(this).set(2, 2, 16, 16);

                if (this.direction == Direction.RIGHT)
                {
                    button.flex().x(1, -18);
                }
            }
            else
            {
                boolean bottom = this.direction == Direction.BOTTOM;

                button.flex().relative(this).set(0, 0, 16, 16).x(1, -18).y(bottom ? 1 : 0, bottom ? -18 : 2);
            }
        }
        else
        {
            GuiIconElement last = this.buttons.elements.get(this.buttons.elements.size() - 1);

            int x = this.direction.isVertical() ? -20 : 0;
            int y = this.direction.isHorizontal() ? 20 : 0;

            button.flex().relative(last).set(x, y, 16, 16);
        }
    }

    /**
     * Switch current panel to given one
     */
    public void setPanel(T panel)
    {
        this.view.setDelegate(panel);
    }

    protected void drawOverlay(GuiContext context)
    {
        if (this.direction == Direction.TOP)
        {
            this.drawBackground(context, this.area.x, this.area.y, this.area.w, 20);
        }
        else if (this.direction == Direction.BOTTOM)
        {
            this.drawBackground(context, this.area.x, this.area.ey() - 20, this.area.w, 20);
        }
        else if (this.direction == Direction.LEFT)
        {
            this.drawBackground(context, this.area.x, this.area.y, 20, this.area.h);
        }
        else
        {
            this.drawBackground(context, this.area.ex() - 20, this.area.y, 20, this.area.h);
        }

        for (int i = 0, c = this.panels.size(); i < c; i++)
        {
            if (this.view.delegate == this.panels.get(i))
            {
                Area area = this.buttons.elements.get(i).area;

                Gui.drawRect(area.x - 2, area.y - 2, area.ex() + 2, area.ey() + 2, 0xaa000000 + McLib.primaryColor.get());
            }
        }
    }

    protected void drawBackground(GuiContext context, int x, int y, int w, int h)
    {}
}