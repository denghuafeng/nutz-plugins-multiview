package org.nutz.plugins.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.web.WebRender;

/**
 * Beetl视图。
 * @author denghuafeng(it@denghuafeng.com)
 *
 */
public class BeetlView extends AbstractTemplateViewResolver {
	public GroupTemplate groupTemplate;

	@Override
	protected void init(String appRoot,ServletContext sc) {
		Configuration cfg = null;
		try {
			cfg = Configuration.defaultConfiguration();
		} catch (IOException e) {
			throw new RuntimeException("加载GroupTemplate失败", e);
		}
		WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
		resourceLoader.setRoot(appRoot);
		groupTemplate = new GroupTemplate(resourceLoader, cfg);
	}

	@Override
	public void render(HttpServletRequest req, HttpServletResponse resp,
			String evalPath, Map<String, Object> sharedVars) throws Throwable {
		groupTemplate.setSharedVars(sharedVars);
		WebRender render = new WebRender(groupTemplate);
		render.render(evalPath, req, resp);
	}

}
