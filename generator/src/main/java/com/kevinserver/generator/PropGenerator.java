/**
 * com.kevinweb.app._tools.prop_gen
 */
package com.kevinserver.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kevinserver.generator.bases.PathKit;
import com.kevinserver.generator.models.DataModel;
import com.kevinserver.generator.models.Entity;
import com.kevinserver.generator.models.Property;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * class : PropGenerator.java
 * @author xukai
 * @date 2016年1月6日
 */
public class PropGenerator {
	private static DataModel createDataModel(File file) throws IOException {
		if (!file.exists()) {
			return null;
		}
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String javaPackage = br.readLine();
		String className = br.readLine();
		String propFile = br.readLine();
		boolean needDebug = br.readLine().equals("debug");
		Entity e = new Entity();
		e.setJavaPackage(javaPackage);
		e.setClassName(className);
		e.setPropFile(propFile);
		e.setNeedDebug(needDebug);
		
		List<Property> list = new ArrayList<Property>();
		List<Property> prop = new ArrayList<Property>();
		if (needDebug) {
			Property p = new Property();
			p.setAnnotationName("isDebug");
			p.setPropertyName("DEBUG");
			p.setJavaType("boolean");
			p.setDefVal("true");
			p.setString(false);
			list.add(p);
		}
		String temp = null;
		while((temp = br.readLine()) != null) {
			String[] strs = temp.split("#");
			
			Property p = new Property();
			p.setAnnotationName(strs[0]);
			p.setPropertyName(strs[1]);
			p.setJavaType(strs[2]);
			p.setString((strs[2].equals("String")));
			p.setDefVal(strs[3].equals("-") ? null : strs[3]);
			p.setNotes(strs.length == 5 ? strs[4] : null);
			
			list.add(p);
			prop.add(p);
		}
		e.setProperties(list);
		
		br.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("entity", e);
		
		DataModel model = new DataModel();
		model.entity = map;
		model.javaFile = toJavaFilename(outDirFile, javaPackage, className);
		model.strPropFile = propFile;
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("properties", handlerPropertiesKey(prop));
		mapp.put("needDebug", needDebug);
		model.prop = mapp;
		
		return model;
	}
	private static List<Property> handlerPropertiesKey(List<Property> list) {
		Map<String, Property> map = new LinkedHashMap<String, Property>();
		for (Property p : list) {
			map.put(p.getAnnotationName(), p);
		}
		list.clear();
		for (String key : map.keySet()) {
			if (key.contains("[") && key.contains("]")) {
				String t = key.substring(key.indexOf("[") + 1, key.indexOf("]"));
				if (map.containsKey(t)) {
					String k = map.get(t).getDefVal();
					map.get(key).setAnnotationName(key.replace("[" + t + "]", k));
				}
				list.add(map.get(key));
			} else {
				list.add(map.get(key));
			}
		}
		
		return list;
	}
	
	private static File toJavaFilename(File outDirFile, String javaPackage, String javaClassName) {
        String packageSubPath = "/src/main/res/" + javaPackage.replace('.', '/');
        File packagePath = new File(outDirFile, packageSubPath);
        File file = new File(packagePath, javaClassName + ".java");
        System.out.println(file.getAbsolutePath());
        if(!packagePath.exists()){
            packagePath.mkdirs();
        }
        return file;
    }
	
	private static File outDirFile;
	private static File outPropDir;
	
	private static void createOutFile(File outputFile, Template template, Map<String, Object> root) throws IOException, TemplateException {
		FileOutputStream fos = new FileOutputStream(outputFile);
		Writer outWriter = new OutputStreamWriter(fos);
		template.process(root, outWriter);
		outWriter.flush();
		outWriter.close();
	}
	
	private static void generate(File file, Template javaTemplate, Template propTemplate) throws IOException, TemplateException {
		DataModel model = createDataModel(file);
		if (model != null) {
			createOutFile(model.javaFile, javaTemplate, model.entity);
			createOutFile(new File(outPropDir, model.strPropFile), propTemplate, model.prop);
		}
	}
	
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		try {
			outDirFile = new File(PathKit.getRootPath());
			outPropDir = new File(outDirFile, "/src/main/res");
			cfg.setDirectoryForTemplateLoading(new File(outDirFile, "/src/main/res/templates"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template javaTemplate = cfg.getTemplate("prop_temp.ftl");
			Template propTemplate = cfg.getTemplate("prop_file.ftl");
			
			File path = new File(outDirFile, "/src/main/res/props");
			File[] files = path.listFiles();
			for (File file : files) {
				generate(file, javaTemplate, propTemplate);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
