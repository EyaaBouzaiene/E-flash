<?php

namespace App\Repository;

use App\data\SearchData;
use App\Entity\Produit2;
use ContainerXcbViMi\PaginatorInterface_82dac15;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;
use Knp\Component\Pager\Pagination\PaginationInterface;
use Knp\Component\Pager\PaginatorInterface;

/**
 * @method Produit2|null find($id, $lockMode = null, $lockVersion = null)
 * @method Produit2|null findOneBy(array $criteria, array $orderBy = null)
 * @method Produit2[]    findAll()
 * @method Produit2[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class Produit2Repository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry, PaginatorInterface $paginator)
    {
        parent::__construct($registry, Produit2::class);
        $this->paginator=$paginator;
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Produit2 $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Produit2 $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }
    /**
     * Récupère les produits en lien avec une recherche
     * @return PaginationInterface
     */
    public function listProduitByCat($id,SearchData $search): PaginationInterface
    {
        $query= $this->createQueryBuilder('p')
            ->join('p.Categorie', 'c')
            ->addSelect('c')
            ->andWhere('c.id=:id')
            ->setParameter('id', $id);
            if (!empty($search->q)) {
                $query = $query
                    ->andWhere('p.NOM LIKE :q')
                    ->setParameter('q', "%{$search->q}%");
            }
    
            if (!empty($search->min)) {
                $query = $query
                    ->andWhere('p.Prix >= :min')
                    ->setParameter('min', $search->min);
            }
    
            if (!empty($search->max)) {
                $query = $query
                    ->andWhere('p.Prix <= :max')
                    ->setParameter('max', $search->max);
            }
    
            if (!empty($search->promo)) {
                $query = $query
                    ->andWhere('p.promo = 1');
            }
    
            if (!empty($search->categories)) {
                $query = $query
                    ->andWhere('c.id IN (:categories)')
                    ->setParameter('categories', $search->categories);
            }

            return $this->paginator->paginate(
                $query,
                $search->page,
                3
            );
           
           
    }
    public function findEntitiesByString($str)
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT e
                FROM App\Entity\Produit2 e
                WHERE e.NOM LIKE :str'
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }
    public function findEntitiesByRate($str)
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT e
                FROM App\Entity\Produit2 e
                WHERE e.NOM LIKE :str'
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }
    public function findEntitiesByCOUNTRY()
    {
        $query = $this->createQueryBuilder('p')
              ->select("p.COUNTRY, count(p)")
              ->groupBy("p.COUNTRY")
              
              ->getQuery()
              ->getResult();
    }

    public function findEntitiesByCOUNTRY2()
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT prov.COUNTRY , count(prov) as items
            FROM App\Entity\Produit2 prov 
            GROUP BY prov.COUNTRY'
            )
            ->getResult();
    }




    public function findEntitiesByRate2()
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT e
                FROM App\Entity\Produit2 e
                WHERE e.rate >=4'
            )
            ->setMaxResults(6)
            ->getResult();
    }

    public function findEntitiesByRate3()
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT p.NOM  as nom ,p.rate  as rate  
            FROM App\Entity\Produit2 p
           '
            )
            ->getResult();
    }

    
    
}