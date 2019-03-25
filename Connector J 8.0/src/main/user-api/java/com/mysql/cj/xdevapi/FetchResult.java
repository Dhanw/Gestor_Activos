/*
 * Copyright (c) 2015, 2018, Oracle and/or its affiliates. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2.0, as published by the
 * Free Software Foundation.
 *
 * This program is also distributed with certain software (including but not
 * limited to OpenSSL) that is licensed under separate terms, as designated in a
 * particular file or component or in included license documentation. The
 * authors of MySQL hereby grant you an additional permission to link the
 * program and your derivative works with the separately licensed software that
 * they have included with MySQL.
 *
 * Without limiting anything contained in the foregoing, this file, which is
 * part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at
 * http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0,
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */
package com.mysql.cj.xdevapi;

import java.util.Iterator;
import java.util.List;

/**
 * A set of elements from a query command.
 *
 * @param <T> the type of element returned from the query (doc or row)
 */
public interface FetchResult<T> extends Iterator<T>, Iterable<T> {

    /**
     * Does this result have data? This indicates that the result was produced
     * from a data-returning query. It does not indicate whether there are more
     * than 0 rows in the result.
     *
     * @return true if has data
     */
    default boolean hasData() {
        return true;
    }

    /**
     * Fetch the next element.
     *
     * @return element of type T
     */
    default T fetchOne() {
        if (hasNext()) {
            return next();
        }
        return null;
    }

    /**
     * Create an iterator over all elements of the result.
     *
     * @return iterator over result items
     */
    default Iterator<T> iterator() {
        return fetchAll().iterator();
    }

    /**
     * How many items are in this result? This method forces internal buffering
     * of the entire result.
     *
     * @return number of elements in result
     */
    long count();

    /**
     * Create a list of all elements in the result forcing internal buffering.
     *
     * @return list of result elements
     */
    List<T> fetchAll();

    /**
     * Count of warnings generated during statement execution. This method
     * forces internal buffering of the result.
     *
     * @return number of warnings
     */
    int getWarningsCount();

    /**
     * Warnings generated during statement execution. This method forces
     * internal buffering of the result.
     *
     * @return iterator over warnings
     */
    Iterator<Warning> getWarnings();
}
