package mchorse.mclib.client.gui.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was brutally stolen from net.minecraft.util.
 *
 * It was also refactored so I could use {@code List<String>} instead of
 * {@code String[]}, and it doesn't require server. It's local tab completer!
 */
@SideOnly(Side.CLIENT)
public class TabCompleter
{
    protected GuiTextField textField;

    protected List<String> allCompletions = Lists.<String> newArrayList();
    protected List<String> completions = Lists.<String> newArrayList();

    protected boolean didComplete;
    protected int index;

    public TabCompleter(GuiTextField textField)
    {
        this.textField = textField;
    }

    public List<String> getCompletions()
    {
        return this.completions;
    }

    public GuiTextField getField()
    {
        return this.textField;
    }

    public void setField(GuiTextField textField)
    {
        this.textField = textField;
    }

    /**
     * Called when tab key pressed. If it's the first time we tried to complete this string, we ask the server for
     * completions. When the server responds, this method gets called again (via setCompletions).
     */
    public void complete()
    {
        int size = this.completions.size();

        if (size != 0 && this.index >= 0 && this.index < size)
        {
            this.textField.setText(this.completions.get(this.index));
        }

        if (this.didComplete)
        {
            this.index++;

            if (this.index >= this.completions.size())
            {
                this.index = 0;
            }
        }
        else
        {
            this.requestCompletions(this.textField.getText());
        }
    }

    private void requestCompletions(String prefix)
    {
        List<String> completions = new ArrayList<String>();

        for (String str : this.allCompletions)
        {
            if (str.toLowerCase().startsWith(prefix.toLowerCase()) || prefix.isEmpty())
            {
                completions.add(str);
            }
        }

        this.setCompletions(completions);
    }

    public void setAllCompletions(List<String> words)
    {
        this.allCompletions.clear();
        this.allCompletions.addAll(words);
    }

    /**
     * Only actually sets completions if they were requested (via requestCompletions)
     */
    public void setCompletions(List<String> words)
    {
        this.didComplete = false;

        this.completions.clear();
        this.completions.addAll(words);
        this.index = 0;

        if (!this.completions.isEmpty())
        {
            this.didComplete = true;
            this.complete();
        }
    }

    /**
     * Called when new text is entered, or backspace pressed
     */
    public void resetDidComplete()
    {
        this.didComplete = false;
        this.completions.clear();
    }
}