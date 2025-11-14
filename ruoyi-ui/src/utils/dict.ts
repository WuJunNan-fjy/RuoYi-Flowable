import { ref, toRefs } from 'vue'
import type { Ref } from 'vue'
import useDictStore from '@/store/modules/dict'
import { getDicts } from '@/api/system/dict/data'

/* ---------- 类型定义 ---------- */
export interface DictItem {
    label: string
    value: string
    elTagType?: string
    elTagClass?: string
}

/* 让 TS 精确知道返回对象里有哪些 key */
export type DictRefs<T extends readonly string[]> = {
    [K in T[number]]: Ref<DictItem[]>
}

export function useDict<T extends readonly string[]>(...args: T): DictRefs<T> {
    const dictMap = ref({} as Record<T[number], DictItem[]>);

    args.forEach((dictType) => {
        dictMap.value[dictType] = [];

        const cache = useDictStore().getDict(dictType);
        if (cache) {
            dictMap.value[dictType] = cache;
            return;
        }

        getDicts(dictType).then((resp: { data: any[] }) => {
            const items: DictItem[] = resp.data.map(p => ({
                label: p.dictLabel,
                value: p.dictValue,
                elTagType: p.listClass,
                elTagClass: p.cssClass,
            }))
            dictMap.value[dictType] = items;
            useDictStore().setDict(dictType, items);
        })
    })

    /* 保持字面量 key 的 Ref 结构返回 */
    return toRefs(dictMap.value) as DictRefs<T>;
}
