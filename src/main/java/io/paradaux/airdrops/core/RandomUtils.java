/*
 * MIT License
 *
 * Copyright (c) 2021 Rían Errity
 * io.paradaux.friendlybot.utils.RandomUtils :  06/02/2021, 10:00
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.paradaux.airdrops.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    private final Random random;

    public RandomUtils() {
        this.random = new Random();
    }

    public RandomUtils(Random random) {
        this.random = random;
    }

    /**
     * Picks a random element from the provided collection, assuming we can't get by index, use an Iterator, otherwise use the index.
     * */
    @CheckReturnValue
    @Nullable
    public <E> E fromCollection(Collection<? extends E> collection) {
        if (collection.size() == 0) {
            return null;
        }

        int index = random.nextInt(collection.size());

        if (collection instanceof List) {
            return ((List<? extends E>) collection).get(index);
        }

        Iterator<? extends E> iterator = collection.iterator();

        for (int i = 0; i < index; i++) {
            iterator.next();
        }

        return iterator.next();
    }

    /**
     * Pick a random element from a JsonArray
     * */
    @CheckReturnValue
    @Nullable
    public JsonElement fromJsonArray(JsonArray array) {
        if (array.size() == 0) {
            return null;
        }

        int index = random.nextInt(array.size());
        Iterator<JsonElement> iterator = array.iterator();

        for (int i = 0; i < index; i++) {
            iterator.next();
        }

        return iterator.next();
    }

    /**
     * Picks a random number between the provided bounds
     * */
    @CheckReturnValue
    public int pickRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return random.nextInt((max - min) + 1) + min;
    }

}