/*
 * Name: FileEyes
 * Version: 1.0
 * Author: Lzagddsg
 * PLZ DO NOT CARRY IT TO ANOTHER PROJECT! [doge]
 */

package com.java.demo;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Gui {

	private Frame frame;
	private MenuBar bar;
	private Menu fileMenu;
	private MenuItem closeItem, openItem, saveItem;
	private FileDialog openDlg, saveDlg;
	private TextArea ta;

	Gui() {
		init();
	}

	public void init() {
		frame = new Frame("FileEyes");
		bar = new MenuBar();
		fileMenu = new Menu("File");
		openItem = new MenuItem("Open file");
		saveItem = new MenuItem("Save file");
		closeItem = new MenuItem("Exit");
		ta = new TextArea();

		openDlg = new FileDialog(frame, "Open file", FileDialog.LOAD);
		saveDlg = new FileDialog(frame, "Save file", FileDialog.SAVE);

		frame.add(ta);
        ta.setVisible(true);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(closeItem);

		bar.add(fileMenu);

		frame.setMenuBar(bar);
		frame.setBounds(700, 300, 600, 500);
		frame.setVisible(true);
		closeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				System.exit(0);
			}
		});
		openItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                // 自动更改项目名称
                frame.setTitle("FileEyes - " + openDlg.getFile());
				// TODO 自动生成的方法存根
				openDlg.setVisible(true);
				String fileName = openDlg.getDirectory() + openDlg.getFile();
				if (openDlg.getFile() != null) {
					File f = new File(fileName);
					// File fileName = new File(openDlg.getFile());
					// ta.append(f.toString());
					ta.setText("");
					BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader(f));
						String line = null;
						while ((line = br.readLine()) != null) {
							ta.append(line + "\r\n");
						}
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					} finally {
						try {
							if (br != null)
								br.close();
						} catch (IOException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
					}
				}
			}
		});
		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				saveDlg.setVisible(true);
				BufferedWriter bw = null;
				String fileName = saveDlg.getDirectory()+saveDlg.getFile();
				if(saveDlg.getFile()!=null)
				{
					 try {
						bw = new BufferedWriter(new FileWriter(fileName));
						String text = ta.getText();
						bw.write(text);
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}finally{
						try {
							if(bw!=null)
								bw.close();
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
				}
			}
		});
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Gui();
	}

}
