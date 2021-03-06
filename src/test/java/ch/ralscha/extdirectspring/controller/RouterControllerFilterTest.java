/**
 * Copyright 2010-2012 Ralph Schaer <ralphschaer@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.ralscha.extdirectspring.controller;

import static org.fest.assertions.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.codehaus.jackson.type.TypeReference;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.ralscha.extdirectspring.bean.ExtDirectResponse;
import ch.ralscha.extdirectspring.provider.Row;

/**
 * Tests for {@link RouterController}.
 * 
 * @author Ralph Schaer
 */
@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/testApplicationContext.xml")
public class RouterControllerFilterTest {

	@Autowired
	private RouterController controller;

	private static List<String> jsonList;

	@BeforeClass
	public static void readJson() throws IOException {
		jsonList = new ArrayList<String>();
		InputStream is = RouterControllerFilterTest.class.getResourceAsStream("/filterjson.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonList.add(line);
		}
		br.close();
		is.close();
	}

	@Test
	public void testFilters() throws IOException {

		int index = 1;
		for (String json : jsonList) {
			MockHttpServletResponse response = new MockHttpServletResponse();
			MockHttpServletRequest request = new MockHttpServletRequest();
			Map<String, Object> edRequest = ControllerUtil.readValue(json, Map.class);

			request.setContent(ControllerUtil.writeAsByte(edRequest));
			controller.router(request, response, Locale.ENGLISH);
			List<ExtDirectResponse> responses = ControllerUtil.readDirectResponses(response.getContentAsByteArray());

			assertThat(responses).hasSize(1);
			ExtDirectResponse resp = responses.get(0);
			assertThat(resp.getAction()).isEqualTo("remoteProviderStoreRead");
			assertThat(resp.getMethod()).isEqualTo("methodFilter");
			assertThat(resp.getType()).isEqualTo("rpc");
			assertThat(resp.getTid()).isEqualTo(index);
			assertThat(resp.getMessage()).isNull();
			assertThat(resp.getWhere()).isNull();
			assertThat(resp.getResult()).isNotNull();

			List<Row> rows = ControllerUtil.convertValue(resp.getResult(), new TypeReference<List<Row>>() {
			});

			assertThat(rows).hasSize(1);
			assertThat(rows.get(0).getId()).isEqualTo(index);

			index++;
		}

		assertThat(index).isEqualTo(15);

	}

}
