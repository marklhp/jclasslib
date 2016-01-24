/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package org.gjt.jclasslib.browser.detail.attributes;

import org.gjt.jclasslib.browser.AbstractDetailPane;
import org.gjt.jclasslib.browser.BrowserServices;
import org.gjt.jclasslib.browser.detail.attributes.code.ByteCodeDetailPane;
import org.gjt.jclasslib.browser.detail.attributes.code.ExceptionTableDetailPane;
import org.gjt.jclasslib.browser.detail.attributes.code.MiscDetailPane;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;

/**
    Detail pane showing a <tt>Code</tt> attribute. Contains three other detail
    panes in its tabbed pane.

    @author <a href="mailto:jclasslib@ej-technologies.com">Ingo Kegel</a>
*/
public class CodeAttributeDetailPane extends AbstractDetailPane {

    private JTabbedPane tabbedPane;
    
    private ExceptionTableDetailPane exceptionTablePane;
    private ByteCodeDetailPane byteCodePane;
    private MiscDetailPane miscPane;
    
    /**
        Constructor.
        @param services the associated browser services.
     */
    public CodeAttributeDetailPane(BrowserServices services) {
        super(services);
    }

    protected void setupComponent() {
        setLayout(new BorderLayout());
        
        add(buildTabbedPane(), BorderLayout.CENTER);
    }
    
    /**
        Get the <tt>ByteCodeDetailPane</tt> showing the code
        of this <tt>Code</tt> attribute.
        @return the <tt>ByteCodeDetailPane</tt>
     */
    public ByteCodeDetailPane getCodeAttributeByteCodeDetailPane() {
        return byteCodePane;
    }
    
    /**
        Select the <tt>ByteCodeDetailPane</tt> showing the code
        of this <tt>Code</tt> attribute.
     */
    public void selectByteCodeDetailPane() {
        tabbedPane.setSelectedIndex(0);
    }
    
    private JTabbedPane buildTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Bytecode", buildByteCodePane().getDisplayComponent());
        tabbedPane.addTab("Exception table", buildExceptionTablePane().getDisplayComponent());
        tabbedPane.addTab("Misc", buildMiscPane().getDisplayComponent());
        
        return tabbedPane;
    }

    private ByteCodeDetailPane buildByteCodePane() {
        byteCodePane = new ByteCodeDetailPane(getBrowserServices());
        return byteCodePane;
    }

    private ExceptionTableDetailPane buildExceptionTablePane() {
        exceptionTablePane = new ExceptionTableDetailPane(getBrowserServices());
        return exceptionTablePane;
    }

    private MiscDetailPane buildMiscPane() {
        miscPane = new MiscDetailPane(getBrowserServices());
        return miscPane;
    }
    
    public void show(TreePath treePath) {

        exceptionTablePane.show(treePath);
        byteCodePane.show(treePath);
        miscPane.show(treePath);
    }

    @Override
    public String getClipboardText() {
        return byteCodePane.getClipboardText();
    }
}
