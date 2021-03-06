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
package ch.ralscha.extdirectspring.bean;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import ch.ralscha.extdirectspring.filter.BooleanFilter;
import ch.ralscha.extdirectspring.filter.DateFilter;
import ch.ralscha.extdirectspring.filter.Filter;
import ch.ralscha.extdirectspring.filter.ListFilter;
import ch.ralscha.extdirectspring.filter.NumericFilter;
import ch.ralscha.extdirectspring.filter.StringFilter;

/**
 * Class representing the request of a DirectStore read call.
 * 
 * @author Ralph Schaer
 */
public class ExtDirectStoreReadRequest {

	private String query;

	private Integer limit;

	private Integer start;

	private Integer page;

	private String dir;

	private String sort;

	private String groupBy;

	private String groupDir;

	private List<SortInfo> sorters;

	private List<GroupInfo> groups;

	private List<Filter> filters;

	private Map<String, Object> params;

	public ExtDirectStoreReadRequest() {
		this.filters = Collections.emptyList();
		this.sorters = Collections.emptyList();
		this.groups = Collections.emptyList();
		this.params = Collections.emptyMap();
	}

	/**
	 * @return the text a user entered into a combobox with queryMode 'remote'
	 */
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the number of rows the DirectStore requests for paging
	 */
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * @return the start row from where to send records back for a paging
	 * request. start = {@link #getLimit()} * ({@link #getPage()}-1)
	 */
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 * @return sorting order. "ASC" or "DESC".<br>
	 * ExtJs 4.x and Touch 2 can send more than one sorters. Use
	 * {@link #getSorters()} instead.
	 * @see #isAscendingSort()
	 * @see #isDescendingSort()
	 */
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * @return true if sorting order is ascending.<br>
	 * ExtJs 4.x and Touch 2 can send more than one sorters. Use
	 * {@link #getSorters()} instead.
	 */
	@JsonIgnore
	public boolean isAscendingSort() {
		return (SortDirection.fromString(getDir()) == SortDirection.ASCENDING);
	}

	/**
	 * @return true if sorting order is descending.<br>
	 * ExtJs 4.x and Touch 2 can send more than one sorters. Use
	 * {@link #getSorters()} instead.
	 */
	@JsonIgnore
	public boolean isDescendingSort() {
		return (SortDirection.fromString(getDir()) == SortDirection.DESCENDING);
	}

	/**
	 * @return the field/property on which the sort should be applied.<br>
	 * ExtJs 4.x and Touch 2 can send more than one sorters. Use
	 * {@link #getSorters()} instead.
	 */
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the field/property name on which the grouping should occur.<br>
	 * ExtJs 4.x and Touch 2 can send more than one group infos. Use
	 * {@link #getGroups()} instead.
	 */
	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	/**
	 * @return sorting order for a grouping request. "ASC" or "DESC".<br>
	 * ExtJs 4.x and Touch 2 can send more than one group info. Use
	 * {@link #getGroups()} instead.
	 */
	public String getGroupDir() {
		return groupDir;
	}

	public void setGroupDir(String groupDir) {
		this.groupDir = groupDir;
	}

	/**
	 * @return true if grouping sorting order is ascending.<br>
	 * ExtJs 4.x and Touch 2 can send more than one group info. Use
	 * {@link #getGroups()} instead.
	 */
	@JsonIgnore
	public boolean isAscendingGroupSort() {
		return (SortDirection.fromString(getGroupDir()) == SortDirection.ASCENDING);
	}

	/**
	 * @return true if grouping sorting order is descending.<br>
	 * ExtJs 4.x and Touch 2 can send more than one group info. Use
	 * {@link #getGroups()} instead.
	 */
	@JsonIgnore
	public boolean isDescendingGroupSort() {
		return (SortDirection.fromString(getGroupDir()) == SortDirection.DESCENDING);
	}

	/**
	 * @return collection of filter implementations
	 * @see BooleanFilter
	 * @see DateFilter
	 * @see ListFilter
	 * @see NumericFilter
	 * @see StringFilter
	 */
	public List<Filter> getFilters() {
		return Collections.unmodifiableList(filters);
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	/**
	 * @return page number of a paging request. page = ({@link #getStart()} /
	 * {@link #getLimit()}) + 1
	 */
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<SortInfo> getSorters() {
		return Collections.unmodifiableList(sorters);
	}

	public void setSorters(List<SortInfo> sorters) {
		this.sorters = sorters;
	}

	public List<GroupInfo> getGroups() {
		return Collections.unmodifiableList(groups);
	}

	public void setGroups(List<GroupInfo> groups) {
		this.groups = groups;
	}

	/**
	 * @return a map with all the keys and values from <code>extraParams</code>
	 */
	public Map<String, Object> getParams() {
		return Collections.unmodifiableMap(params);
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "ExtDirectStoreReadRequest [query=" + query + ", limit=" + limit + ", start=" + start + ", page=" + page
				+ ", dir=" + dir + ", sort=" + sort + ", groupBy=" + groupBy + ", groupDir=" + groupDir + ", sorters="
				+ sorters + ", groups=" + groups + ", filters=" + filters + ", params=" + params + "]";
	}

}
